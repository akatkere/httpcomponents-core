/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.hc.core5.http.support;

import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.Method;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ProtocolVersion;
import org.apache.hc.core5.http.message.BasicHttpRequest;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Builder for {@link BasicHttpRequest} instances.
 *
 * @since 5.1
 */
public abstract class AbstractRequestBuilder<T> extends AbstractMessageBuilder<T> {

    private String method;
    private URI uri;
    private Charset charset;
    private List<NameValuePair> parameters;

    protected AbstractRequestBuilder(final String method) {
        super();
        this.method = method;
    }

    protected AbstractRequestBuilder(final Method method) {
        this(method.name());
    }

    protected AbstractRequestBuilder(final String method, final URI uri) {
        super();
        this.method = method;
        this.uri = uri;
    }

    protected AbstractRequestBuilder(final Method method, final URI uri) {
        this(method.name(), uri);
    }

    protected AbstractRequestBuilder(final Method method, final String uri) {
        this(method.name(), uri != null ? URI.create(uri) : null);
    }

    protected AbstractRequestBuilder(final String method, final String uri) {
        this(method, uri != null ? URI.create(uri) : null);
    }

    public String getMethod() {
        return method;
    }

    @Override
    public AbstractRequestBuilder<T> setVersion(final ProtocolVersion version) {
        super.setVersion(version);
        return this;
    }

    public URI getUri() {
        return uri;
    }

    public AbstractRequestBuilder<T> setUri(final URI uri) {
        this.uri = uri;
        return this;
    }

    public AbstractRequestBuilder<T> setUri(final String uri) {
        this.uri = uri != null ? URI.create(uri) : null;
        return this;
    }

    @Override
    public AbstractRequestBuilder<T> setHeaders(final Header... headers) {
        super.setHeaders(headers);
        return this;
    }

    @Override
    public AbstractRequestBuilder<T> addHeader(final Header header) {
        super.addHeader(header);
        return this;
    }

    @Override
    public AbstractRequestBuilder<T> addHeader(final String name, final String value) {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public AbstractRequestBuilder<T> removeHeader(final Header header) {
        super.removeHeader(header);
        return this;
    }

    @Override
    public AbstractRequestBuilder<T> removeHeaders(final String name) {
        super.removeHeaders(name);
        return this;
    }

    @Override
    public AbstractRequestBuilder<T> setHeader(final Header header) {
        super.setHeader(header);
        return this;
    }

    @Override
    public AbstractRequestBuilder<T> setHeader(final String name, final String value) {
        super.setHeader(name, value);
        return this;
    }

    public Charset getCharset() {
        return charset;
    }

    public AbstractRequestBuilder<T> setCharset(final Charset charset) {
        this.charset = charset;
        return this;
    }

    public List<NameValuePair> getParameters() {
        return parameters != null ? new ArrayList<>(parameters) : null;
    }

    public AbstractRequestBuilder<T> addParameter(final NameValuePair nvp) {
        if (nvp == null) {
            return this;
        }
        if (parameters == null) {
            parameters = new LinkedList<>();
        }
        parameters.add(nvp);
        return this;
    }

    public AbstractRequestBuilder<T> addParameter(final String name, final String value) {
        return addParameter(new BasicNameValuePair(name, value));
    }

    public AbstractRequestBuilder<T> addParameters(final NameValuePair... nvps) {
        for (final NameValuePair nvp : nvps) {
            addParameter(nvp);
        }
        return this;
    }

}