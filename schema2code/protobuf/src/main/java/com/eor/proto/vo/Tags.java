// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.eor.proto.vo;

/**
 * Protobuf type {@code protoRoot.Tags}
 */
public final class Tags extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:protoRoot.Tags)
    TagsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Tags.newBuilder() to construct.
  private Tags(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Tags() {
    tag_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Tags();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Tags(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              tag_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000001;
            }
            tag_.add(s);
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        tag_ = tag_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.eor.proto.vo.UserProtos.internal_static_protoRoot_Tags_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.eor.proto.vo.UserProtos.internal_static_protoRoot_Tags_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.eor.proto.vo.Tags.class, com.eor.proto.vo.Tags.Builder.class);
  }

  public static final int TAG_FIELD_NUMBER = 1;
  private com.google.protobuf.LazyStringList tag_;
  /**
   * <code>repeated string tag = 1;</code>
   * @return A list containing the tag.
   */
  public com.google.protobuf.ProtocolStringList
      getTagList() {
    return tag_;
  }
  /**
   * <code>repeated string tag = 1;</code>
   * @return The count of tag.
   */
  public int getTagCount() {
    return tag_.size();
  }
  /**
   * <code>repeated string tag = 1;</code>
   * @param index The index of the element to return.
   * @return The tag at the given index.
   */
  public java.lang.String getTag(int index) {
    return tag_.get(index);
  }
  /**
   * <code>repeated string tag = 1;</code>
   * @param index The index of the value to return.
   * @return The bytes of the tag at the given index.
   */
  public com.google.protobuf.ByteString
      getTagBytes(int index) {
    return tag_.getByteString(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < tag_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, tag_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < tag_.size(); i++) {
        dataSize += computeStringSizeNoTag(tag_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getTagList().size();
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.eor.proto.vo.Tags)) {
      return super.equals(obj);
    }
    com.eor.proto.vo.Tags other = (com.eor.proto.vo.Tags) obj;

    if (!getTagList()
        .equals(other.getTagList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getTagCount() > 0) {
      hash = (37 * hash) + TAG_FIELD_NUMBER;
      hash = (53 * hash) + getTagList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.eor.proto.vo.Tags parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.eor.proto.vo.Tags parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.eor.proto.vo.Tags parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.eor.proto.vo.Tags parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.eor.proto.vo.Tags parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.eor.proto.vo.Tags parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.eor.proto.vo.Tags parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.eor.proto.vo.Tags parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.eor.proto.vo.Tags parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.eor.proto.vo.Tags parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.eor.proto.vo.Tags parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.eor.proto.vo.Tags parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.eor.proto.vo.Tags prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code protoRoot.Tags}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:protoRoot.Tags)
      com.eor.proto.vo.TagsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.eor.proto.vo.UserProtos.internal_static_protoRoot_Tags_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.eor.proto.vo.UserProtos.internal_static_protoRoot_Tags_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.eor.proto.vo.Tags.class, com.eor.proto.vo.Tags.Builder.class);
    }

    // Construct using com.eor.proto.vo.Tags.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      tag_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.eor.proto.vo.UserProtos.internal_static_protoRoot_Tags_descriptor;
    }

    @java.lang.Override
    public com.eor.proto.vo.Tags getDefaultInstanceForType() {
      return com.eor.proto.vo.Tags.getDefaultInstance();
    }

    @java.lang.Override
    public com.eor.proto.vo.Tags build() {
      com.eor.proto.vo.Tags result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.eor.proto.vo.Tags buildPartial() {
      com.eor.proto.vo.Tags result = new com.eor.proto.vo.Tags(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        tag_ = tag_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.tag_ = tag_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.eor.proto.vo.Tags) {
        return mergeFrom((com.eor.proto.vo.Tags)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.eor.proto.vo.Tags other) {
      if (other == com.eor.proto.vo.Tags.getDefaultInstance()) return this;
      if (!other.tag_.isEmpty()) {
        if (tag_.isEmpty()) {
          tag_ = other.tag_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureTagIsMutable();
          tag_.addAll(other.tag_);
        }
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.eor.proto.vo.Tags parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.eor.proto.vo.Tags) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.LazyStringList tag_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureTagIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        tag_ = new com.google.protobuf.LazyStringArrayList(tag_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated string tag = 1;</code>
     * @return A list containing the tag.
     */
    public com.google.protobuf.ProtocolStringList
        getTagList() {
      return tag_.getUnmodifiableView();
    }
    /**
     * <code>repeated string tag = 1;</code>
     * @return The count of tag.
     */
    public int getTagCount() {
      return tag_.size();
    }
    /**
     * <code>repeated string tag = 1;</code>
     * @param index The index of the element to return.
     * @return The tag at the given index.
     */
    public java.lang.String getTag(int index) {
      return tag_.get(index);
    }
    /**
     * <code>repeated string tag = 1;</code>
     * @param index The index of the value to return.
     * @return The bytes of the tag at the given index.
     */
    public com.google.protobuf.ByteString
        getTagBytes(int index) {
      return tag_.getByteString(index);
    }
    /**
     * <code>repeated string tag = 1;</code>
     * @param index The index to set the value at.
     * @param value The tag to set.
     * @return This builder for chaining.
     */
    public Builder setTag(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureTagIsMutable();
      tag_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string tag = 1;</code>
     * @param value The tag to add.
     * @return This builder for chaining.
     */
    public Builder addTag(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureTagIsMutable();
      tag_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string tag = 1;</code>
     * @param values The tag to add.
     * @return This builder for chaining.
     */
    public Builder addAllTag(
        java.lang.Iterable<java.lang.String> values) {
      ensureTagIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, tag_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string tag = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearTag() {
      tag_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string tag = 1;</code>
     * @param value The bytes of the tag to add.
     * @return This builder for chaining.
     */
    public Builder addTagBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureTagIsMutable();
      tag_.add(value);
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:protoRoot.Tags)
  }

  // @@protoc_insertion_point(class_scope:protoRoot.Tags)
  private static final com.eor.proto.vo.Tags DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.eor.proto.vo.Tags();
  }

  public static com.eor.proto.vo.Tags getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Tags>
      PARSER = new com.google.protobuf.AbstractParser<Tags>() {
    @java.lang.Override
    public Tags parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Tags(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Tags> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Tags> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.eor.proto.vo.Tags getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

