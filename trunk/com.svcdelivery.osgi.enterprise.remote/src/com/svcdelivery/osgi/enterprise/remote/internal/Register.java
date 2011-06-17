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
import java.util.List;
import java.util.Map;

/**
 * @author Nick Wilson
 */
public class Register implements Serializable
{
	/**
	 * Serial UID.
	 */
	private static final long		serialVersionUID	= -6500394352980862223L;
	private ServiceId				id;
	private List< String >			interfaces;
	private Map< String, Object >	properties;

	/**
	 * @return returns the id.
	 */
	public ServiceId getId()
	{
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId( ServiceId id )
	{
		this.id = id;
	}

	/**
	 * @return returns the interfaces.
	 */
	public List< String > getInterfaces()
	{
		return interfaces;
	}

	/**
	 * @param interfaces The interfaces to set.
	 */
	public void setInterfaces( List< String > interfaces )
	{
		this.interfaces = interfaces;
	}

	/**
	 * @return returns the properties.
	 */
	public Map< String, Object > getProperties()
	{
		return properties;
	}

	/**
	 * @param properties The properties to set.
	 */
	public void setProperties( Map< String, Object > properties )
	{
		this.properties = properties;
	}

}
