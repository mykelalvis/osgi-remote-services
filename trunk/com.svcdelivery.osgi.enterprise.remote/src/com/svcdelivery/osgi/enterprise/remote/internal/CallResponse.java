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

import java.io.Serializable;

/**
 * @author Nick Wilson
 */
public class CallResponse implements Serializable
{

	/**
	 * Serial UID.
	 */
	private static final long	serialVersionUID	= -1041158087817567351L;

	private long				callId;
	private ServiceId			serviceId;
	private Object				response;
	private Throwable			exception;

	public CallResponse( long callId, ServiceId serviceId )
	{
		this.callId = callId;
		this.serviceId = serviceId;
	}

	/**
	 * @return returns the response.
	 */
	public Object getResponse()
	{
		return response;
	}

	/**
	 * @param response The response to set.
	 */
	public void setResponse( Object response )
	{
		this.response = response;
	}

	/**
	 * @return returns the exception.
	 */
	public Throwable getException()
	{
		return exception;
	}

	/**
	 * @param exception The exception to set.
	 */
	public void setException( Throwable exception )
	{
		this.exception = exception;
	}

	/**
	 * @return returns the callId.
	 */
	public long getCallId()
	{
		return callId;
	}

	/**
	 * @return returns the serviceId.
	 */
	public ServiceId getServiceId()
	{
		return serviceId;
	}

}
