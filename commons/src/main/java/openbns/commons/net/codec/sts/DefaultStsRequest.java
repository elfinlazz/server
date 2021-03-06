/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package openbns.commons.net.codec.sts;

import io.netty.util.internal.StringUtil;

/**
 * The default {@link StsRequest} implementation.
 */
public class DefaultStsRequest extends DefaultStsMessage implements StsRequest
{

    private StsMethod method;
    private String uri;

    /**
     * Creates a new instance.
     *
     * @param stsVersion the HTTP version of the request
     * @param method      the HTTP getMethod of the request
     * @param uri         the URI or path of the request
     */
    public DefaultStsRequest( StsVersion stsVersion, StsMethod method, String uri ) {
        this( stsVersion, method, uri, true);
    }

    /**
     * Creates a new instance.
     *
     * @param stsVersion       the HTTP version of the request
     * @param method            the HTTP getMethod of the request
     * @param uri               the URI or path of the request
     * @param validateHeaders   validate the headers when adding them
     */
    public DefaultStsRequest( StsVersion stsVersion, StsMethod method, String uri, boolean validateHeaders ) {
        super( stsVersion, validateHeaders);
        if (method == null) {
            throw new NullPointerException("method");
        }
        if (uri == null) {
            throw new NullPointerException("uri");
        }
        this.method = method;
        this.uri = uri;
    }

    @Override
    public StsMethod getMethod() {
        return method;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public StsRequest setMethod(StsMethod method) {
        if (method == null) {
            throw new NullPointerException("method");
        }
        this.method = method;
        return this;
    }

    @Override
    public StsRequest setUri(String uri) {
        if (uri == null) {
            throw new NullPointerException("uri");
        }
        this.uri = uri;
        return this;
    }

    @Override
    public StsRequest setProtocolVersion(StsVersion version) {
        super.setProtocolVersion(version);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(StringUtil.simpleClassName(this));
        buf.append(", decodeResult: ");
        buf.append(getDecoderResult());
        buf.append(')');
        buf.append(StringUtil.NEWLINE);
        buf.append(getMethod().toString());
        buf.append(' ');
        buf.append(getUri());
        buf.append(' ');
        buf.append(getProtocolVersion().text());
        buf.append(StringUtil.NEWLINE);
        appendHeaders(buf);

        // Remove the last newline.
        buf.setLength(buf.length() - StringUtil.NEWLINE.length());
        return buf.toString();
    }
}
