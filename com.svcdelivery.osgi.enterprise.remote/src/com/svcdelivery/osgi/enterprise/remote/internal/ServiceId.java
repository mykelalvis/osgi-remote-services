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
 * A unique identifier for a service running on a specific host.
 * 
 * @author Nick Wilson
 */
public class ServiceId implements Serializable
{
	/**
	 * Serial UID.
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * The host for the service. This is the GMS server name.
	 */
	private String				host;

	/**
	 * An ID unique to the service, but may be registered by multiple hosts.
	 */
	private String				id;

	/**
	 * @param host The host for the service. This is the GMS server name.
	 * @param id An ID unique to the service, but may be registered by multiple hosts.
	 */
	public ServiceId( String host, String id )
	{
		this.host = host;
		this.id = id;
	}

	/**
	 * @return returns the host.
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * @return returns the id.
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return host.hashCode() + id.hashCode();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		return toString().equals( obj.toString() );
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return host + ":" + id;
	}

}
