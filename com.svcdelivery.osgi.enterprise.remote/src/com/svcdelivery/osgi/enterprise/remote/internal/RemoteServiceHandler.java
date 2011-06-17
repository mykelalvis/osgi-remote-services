/**
 * Copyright 2010 Nick Wilson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.svcdelivery.osgi.enterprise.remote.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.sun.enterprise.ee.cms.core.GMSException;
import com.sun.enterprise.ee.cms.core.GroupHandle;
import com.sun.enterprise.ee.cms.core.GroupManagementService;

/**
 * @author Nick Wilson
 */
public class RemoteServiceHandler implements InvocationHandler
{
	private GroupManagementService		gms;
	private IdFactory					idf;
	private ServiceId					id;
	private Map< Long, Call >			liveCalls;
	private Map< Long, CallResponse >	responses;

	/**
	 * @param gms
	 * @param id
	 */
	public RemoteServiceHandler( GroupManagementService gms, IdFactory idf, ServiceId id )
	{
		this.gms = gms;
		this.idf = idf;
		this.id = id;
		liveCalls = new HashMap< Long, Call >();
		responses = new HashMap< Long, CallResponse >();
	}

	/**
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method,
	 *      java.lang.Object[])
	 */
	@Override
	public Object invoke( Object proxy, Method method, Object[] args ) throws Throwable
	{
		Object response = null;
		long callId = idf.next();
		Call call = new Call( callId, id, Activator.getSignature(method) );
		liveCalls.put( callId, call );
		try
		{
			GroupHandle gh = gms.getGroupHandle();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream( os );
			oos.writeObject( call );
			oos.flush();
			gh.sendMessage( id.getHost(), Activator.COMPONENT_NAME, os.toByteArray() );

			long delay = 5000;
			long end = System.currentTimeMillis() + delay;
			while ( delay > 0 )
			{
				try
				{
					synchronized ( call )
					{
						call.wait( delay );
					}
				}
				catch ( InterruptedException e )
				{
				}
				if ( responses.containsKey( callId ) )
				{
					delay = 0;
				}
				else
				{
					delay = end - System.currentTimeMillis();
				}
			}
			response = responses.get( callId );
		}
		catch ( GMSException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			liveCalls.remove( callId );
			responses.remove( callId );
		}
		return response;
	}

	/**
	 * @param response
	 */
	public void handleResponse( CallResponse response )
	{
		long callId = response.getCallId();
		Call call = liveCalls.get( callId );
		if ( call != null )
		{
			responses.put( callId, response );
			synchronized ( call )
			{
				call.notify();
			}
		}
	}

}
