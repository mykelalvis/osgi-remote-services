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

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * @author Nick Wilson
 */
public class BundleProxyClassLoader extends ClassLoader
{
	private BundleContext	ctx;

	public BundleProxyClassLoader( BundleContext ctx )
	{
		this.ctx = ctx;
	}

	/**
	 * @see java.lang.ClassLoader#findClass(java.lang.String)
	 */
	@Override
	protected Class< ? > findClass( String name ) throws ClassNotFoundException
	{
		Class< ? > cls = null;
		for ( Bundle bundle : ctx.getBundles() )
		{
			try
			{
				cls = bundle.loadClass( name );
				break;
			}
			catch ( ClassNotFoundException e )
			{
				// Ignore, it might be from another bundle.
			}
		}
		if ( cls == null )
		{
			throw new ClassNotFoundException( name );
		}
		return cls;
	}

}
