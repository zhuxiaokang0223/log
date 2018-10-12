package org.apache.logging.log4j.core.layout;


import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.util.KeyValuePair;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Describe: 自定义CustomJsonLayout
 *
 *          继承AbstractJacksonLayout类
 *
 * @author : ZhuXiaokang
 * @mail : xiaokang.zhu@pactera.com
 * @date : 2018/9/29 9:05
 * Attention:
 * Modify:
 */
@Plugin(name = "CustomJsonLayout",
        category = Node.CATEGORY,
        elementType = Layout.ELEMENT_TYPE,
        printObject = true)
public class CustomJsonLayout extends AbstractJacksonLayout {

    private static final String DEFAULT_FOOTER = "]";
    private static final String DEFAULT_HEADER = "[";
    static final String CONTENT_TYPE = "application/json";

    /** @deprecated */
    @Deprecated
    protected CustomJsonLayout(Configuration config, boolean locationInfo, boolean properties, boolean encodeThreadContextAsList, boolean complete, boolean compact, boolean eventEol, String headerPattern, String footerPattern, Charset charset, boolean includeStacktrace) {
        super(config, (new org.apache.logging.log4j.core.layout.JacksonFactory.JSON(encodeThreadContextAsList, includeStacktrace, false, false)).newWriter(locationInfo, properties, compact), charset, compact, complete, eventEol, PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(headerPattern).setDefaultPattern("[").build(), PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(footerPattern).setDefaultPattern("]").build(), false, (KeyValuePair[])null);
    }

    private CustomJsonLayout(Configuration config, boolean locationInfo, boolean properties, boolean encodeThreadContextAsList, boolean complete, boolean compact, boolean eventEol, String headerPattern, String footerPattern, Charset charset, boolean includeStacktrace, boolean stacktraceAsString, boolean includeNullDelimiter, KeyValuePair[] additionalFields, boolean objectMessageAsJsonObject) {
        super(config, (new org.apache.logging.log4j.core.layout.JacksonFactory.JSON(encodeThreadContextAsList, includeStacktrace, stacktraceAsString, objectMessageAsJsonObject)).newWriter(locationInfo, properties, compact), charset, compact, complete, eventEol, PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(headerPattern).setDefaultPattern("[").build(), PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(footerPattern).setDefaultPattern("]").build(), includeNullDelimiter, additionalFields);
    }

    @Override
    public byte[] getHeader() {
        if(!this.complete) {
            return null;
        } else {
            StringBuilder buf = new StringBuilder();
            String str = this.serializeToString(this.getHeaderSerializer());
            if(str != null) {
                buf.append(str);
            }

            buf.append(this.eol);
            return this.getBytes(buf.toString());
        }
    }

    @Override
    public byte[] getFooter() {
        if(!this.complete) {
            return null;
        } else {
            StringBuilder buf = new StringBuilder();
            buf.append(this.eol);
            String str = this.serializeToString(this.getFooterSerializer());
            if(str != null) {
                buf.append(str);
            }

            buf.append(this.eol);
            return this.getBytes(buf.toString());
        }
    }

    @Override
    public java.util.Map<String, String> getContentFormat() {
        java.util.HashMap result = new java.util.HashMap();
        result.put("version", "2.0");
        return result;
    }

    @Override
    public String getContentType() {
        return "application/json; charset=" + this.getCharset();
    }

    /** @deprecated */
    @Deprecated
    public static CustomJsonLayout createLayout(Configuration config, boolean locationInfo, boolean properties, boolean propertiesAsList, boolean complete, boolean compact, boolean eventEol, String headerPattern, String footerPattern, Charset charset, boolean includeStacktrace) {
        boolean encodeThreadContextAsList = properties && propertiesAsList;
        return new CustomJsonLayout(config, locationInfo, properties, encodeThreadContextAsList, complete, compact, eventEol, headerPattern, footerPattern, charset, includeStacktrace, false, false, (KeyValuePair[])null, false);
    }

    @PluginBuilderFactory
    public static <B extends CustomJsonLayout.Builder<B>> B newBuilder() {
        return new CustomJsonLayout.Builder<B>().asBuilder();
    }

    public static CustomJsonLayout createDefaultLayout() {
        return new CustomJsonLayout(new DefaultConfiguration(), false, false, false, false, false, false, "[", "]", java.nio.charset.StandardCharsets.UTF_8, true, false, false, (KeyValuePair[])null, false);
    }

    @Override
    public void toSerializable(LogEvent event, java.io.Writer writer) throws IOException {
        if(this.complete && this.eventCount > 0L) {
            writer.append(", ");
        }

        super.toSerializable(event, writer);
    }

    public static class Builder<B extends CustomJsonLayout.Builder<B>> extends AbstractJacksonLayout.Builder<B> implements org.apache.logging.log4j.core.util.Builder<CustomJsonLayout> {
        @PluginBuilderAttribute
        private boolean propertiesAsList;
        @PluginBuilderAttribute
        private boolean objectMessageAsJsonObject;
        @PluginElement("AdditionalField")
        private KeyValuePair[] additionalFields;

        public Builder() {
            this.setCharset(java.nio.charset.StandardCharsets.UTF_8);
        }

        @Override
        public CustomJsonLayout build() {
            boolean encodeThreadContextAsList = this.isProperties() && this.propertiesAsList;
            String headerPattern = this.toStringOrNull(this.getHeader());
            String footerPattern = this.toStringOrNull(this.getFooter());
            return new CustomJsonLayout(this.getConfiguration(), this.isLocationInfo(), this.isProperties(), encodeThreadContextAsList, this.isComplete(), this.isCompact(), this.getEventEol(), headerPattern, footerPattern, this.getCharset(), this.isIncludeStacktrace(), this.isStacktraceAsString(), this.isIncludeNullDelimiter(), this.getAdditionalFields(), this.getObjectMessageAsJsonObject());
        }

        public boolean isPropertiesAsList() {
            return this.propertiesAsList;
        }

        public B setPropertiesAsList(boolean propertiesAsList) {
            this.propertiesAsList = propertiesAsList;
            return this.asBuilder();
        }

        public boolean getObjectMessageAsJsonObject() {
            return this.objectMessageAsJsonObject;
        }

        public B setObjectMessageAsJsonObject(boolean objectMessageAsJsonObject) {
            this.objectMessageAsJsonObject = objectMessageAsJsonObject;
            return this.asBuilder();
        }

        @Override
        public KeyValuePair[] getAdditionalFields() {
            return this.additionalFields;
        }

        @Override
        public B setAdditionalFields(KeyValuePair[] additionalFields) {
            this.additionalFields = additionalFields;
            return this.asBuilder();
        }
    }
} 
