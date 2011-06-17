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
public class Call implements Serializable
{
	/**
	 * Serial UID.
	 */
	private static final long	serialVersionUID	= -1699980685467766360L;

	private long				callId;
	private ServiceId			serviceId;
	private String				method;
	private Object[]			parameters;

	public Call( long callId, ServiceId serviceId, String method )
	{
		this.callId = callId;
		this.serviceId = serviceId;
		this.method = method;
	}

	/**
	 * @return returns the parameters.
	 */
	public Object[] getParameters()
	{
		return parameters;
	}

	/**
	 * @param parameters The parameters to set.
	 */
	public void setParameters( Object[] parameters )
	{
		this.parameters = parameters;
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

	/**
	 * @return returns the method.
	 */
	public String getMethod()
	{
		return method;
	}

}
