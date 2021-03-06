package fr.vsct.tock.nlp.front.shared.parser

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.litote.jackson.JacksonModuleServiceLoader

class ParseQuery_Serializer : StdSerializer<ParseQuery>(ParseQuery::class.java),
        JacksonModuleServiceLoader {
    override fun module() = SimpleModule().addSerializer(this)

    override fun serialize(
            value: ParseQuery,
            gen: JsonGenerator,
            serializers: SerializerProvider
    ) {
        gen.writeStartObject()
        gen.writeFieldName("queries")
        val _queries_ = value.queries
        serializers.defaultSerializeValue(_queries_, gen)
        gen.writeFieldName("namespace")
        val _namespace_ = value.namespace
        gen.writeString(_namespace_)
        gen.writeFieldName("applicationName")
        val _applicationName_ = value.applicationName
        gen.writeString(_applicationName_)
        gen.writeFieldName("context")
        val _context_ = value.context
        serializers.defaultSerializeValue(_context_, gen)
        gen.writeFieldName("state")
        val _state_ = value.state
        serializers.defaultSerializeValue(_state_, gen)
        gen.writeFieldName("intentsSubset")
        val _intentsSubset_ = value.intentsSubset
        serializers.defaultSerializeValue(_intentsSubset_, gen)
        gen.writeEndObject()
    }
}
