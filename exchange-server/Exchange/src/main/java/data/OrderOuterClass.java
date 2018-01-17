// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: order.proto

package data;

public final class OrderOuterClass {
  private OrderOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface OrderOrBuilder extends
      // @@protoc_insertion_point(interface_extends:order.Order)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required bool confirmation = 1;</code>
     */
    boolean hasConfirmation();
    /**
     * <code>required bool confirmation = 1;</code>
     */
    boolean getConfirmation();

    /**
     * <pre>
     *true -&gt; BUY false -&gt; SELL
     * </pre>
     *
     * <code>required bool type = 2;</code>
     */
    boolean hasType();
    /**
     * <pre>
     *true -&gt; BUY false -&gt; SELL
     * </pre>
     *
     * <code>required bool type = 2;</code>
     */
    boolean getType();

    /**
     * <code>required string symbol = 3;</code>
     */
    boolean hasSymbol();
    /**
     * <code>required string symbol = 3;</code>
     */
    java.lang.String getSymbol();
    /**
     * <code>required string symbol = 3;</code>
     */
    com.google.protobuf.ByteString
        getSymbolBytes();

    /**
     * <code>required int32 quantity = 4;</code>
     */
    boolean hasQuantity();
    /**
     * <code>required int32 quantity = 4;</code>
     */
    int getQuantity();

    /**
     * <code>required double price = 5;</code>
     */
    boolean hasPrice();
    /**
     * <code>required double price = 5;</code>
     */
    double getPrice();

    /**
     * <code>required string user = 6;</code>
     */
    boolean hasUser();
    /**
     * <code>required string user = 6;</code>
     */
    java.lang.String getUser();
    /**
     * <code>required string user = 6;</code>
     */
    com.google.protobuf.ByteString
        getUserBytes();
  }
  /**
   * Protobuf type {@code order.Order}
   */
  public  static final class Order extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:order.Order)
      OrderOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Order.newBuilder() to construct.
    private Order(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Order() {
      confirmation_ = false;
      type_ = false;
      symbol_ = "";
      quantity_ = 0;
      price_ = 0D;
      user_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Order(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
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
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              confirmation_ = input.readBool();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              type_ = input.readBool();
              break;
            }
            case 26: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000004;
              symbol_ = bs;
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              quantity_ = input.readInt32();
              break;
            }
            case 41: {
              bitField0_ |= 0x00000010;
              price_ = input.readDouble();
              break;
            }
            case 50: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000020;
              user_ = bs;
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
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return data.OrderOuterClass.internal_static_order_Order_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return data.OrderOuterClass.internal_static_order_Order_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              data.OrderOuterClass.Order.class, data.OrderOuterClass.Order.Builder.class);
    }

    private int bitField0_;
    public static final int CONFIRMATION_FIELD_NUMBER = 1;
    private boolean confirmation_;
    /**
     * <code>required bool confirmation = 1;</code>
     */
    public boolean hasConfirmation() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required bool confirmation = 1;</code>
     */
    public boolean getConfirmation() {
      return confirmation_;
    }

    public static final int TYPE_FIELD_NUMBER = 2;
    private boolean type_;
    /**
     * <pre>
     *true -&gt; BUY false -&gt; SELL
     * </pre>
     *
     * <code>required bool type = 2;</code>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <pre>
     *true -&gt; BUY false -&gt; SELL
     * </pre>
     *
     * <code>required bool type = 2;</code>
     */
    public boolean getType() {
      return type_;
    }

    public static final int SYMBOL_FIELD_NUMBER = 3;
    private volatile java.lang.Object symbol_;
    /**
     * <code>required string symbol = 3;</code>
     */
    public boolean hasSymbol() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required string symbol = 3;</code>
     */
    public java.lang.String getSymbol() {
      java.lang.Object ref = symbol_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          symbol_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string symbol = 3;</code>
     */
    public com.google.protobuf.ByteString
        getSymbolBytes() {
      java.lang.Object ref = symbol_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        symbol_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int QUANTITY_FIELD_NUMBER = 4;
    private int quantity_;
    /**
     * <code>required int32 quantity = 4;</code>
     */
    public boolean hasQuantity() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required int32 quantity = 4;</code>
     */
    public int getQuantity() {
      return quantity_;
    }

    public static final int PRICE_FIELD_NUMBER = 5;
    private double price_;
    /**
     * <code>required double price = 5;</code>
     */
    public boolean hasPrice() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required double price = 5;</code>
     */
    public double getPrice() {
      return price_;
    }

    public static final int USER_FIELD_NUMBER = 6;
    private volatile java.lang.Object user_;
    /**
     * <code>required string user = 6;</code>
     */
    public boolean hasUser() {
      return ((bitField0_ & 0x00000020) == 0x00000020);
    }
    /**
     * <code>required string user = 6;</code>
     */
    public java.lang.String getUser() {
      java.lang.Object ref = user_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          user_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string user = 6;</code>
     */
    public com.google.protobuf.ByteString
        getUserBytes() {
      java.lang.Object ref = user_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        user_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasConfirmation()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasSymbol()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasQuantity()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasPrice()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUser()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBool(1, confirmation_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBool(2, type_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, symbol_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, quantity_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeDouble(5, price_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 6, user_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, confirmation_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(2, type_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, symbol_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, quantity_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeDoubleSize(5, price_);
      }
      if (((bitField0_ & 0x00000020) == 0x00000020)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, user_);
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
      if (!(obj instanceof data.OrderOuterClass.Order)) {
        return super.equals(obj);
      }
      data.OrderOuterClass.Order other = (data.OrderOuterClass.Order) obj;

      boolean result = true;
      result = result && (hasConfirmation() == other.hasConfirmation());
      if (hasConfirmation()) {
        result = result && (getConfirmation()
            == other.getConfirmation());
      }
      result = result && (hasType() == other.hasType());
      if (hasType()) {
        result = result && (getType()
            == other.getType());
      }
      result = result && (hasSymbol() == other.hasSymbol());
      if (hasSymbol()) {
        result = result && getSymbol()
            .equals(other.getSymbol());
      }
      result = result && (hasQuantity() == other.hasQuantity());
      if (hasQuantity()) {
        result = result && (getQuantity()
            == other.getQuantity());
      }
      result = result && (hasPrice() == other.hasPrice());
      if (hasPrice()) {
        result = result && (
            java.lang.Double.doubleToLongBits(getPrice())
            == java.lang.Double.doubleToLongBits(
                other.getPrice()));
      }
      result = result && (hasUser() == other.hasUser());
      if (hasUser()) {
        result = result && getUser()
            .equals(other.getUser());
      }
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasConfirmation()) {
        hash = (37 * hash) + CONFIRMATION_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
            getConfirmation());
      }
      if (hasType()) {
        hash = (37 * hash) + TYPE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
            getType());
      }
      if (hasSymbol()) {
        hash = (37 * hash) + SYMBOL_FIELD_NUMBER;
        hash = (53 * hash) + getSymbol().hashCode();
      }
      if (hasQuantity()) {
        hash = (37 * hash) + QUANTITY_FIELD_NUMBER;
        hash = (53 * hash) + getQuantity();
      }
      if (hasPrice()) {
        hash = (37 * hash) + PRICE_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            java.lang.Double.doubleToLongBits(getPrice()));
      }
      if (hasUser()) {
        hash = (37 * hash) + USER_FIELD_NUMBER;
        hash = (53 * hash) + getUser().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static data.OrderOuterClass.Order parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static data.OrderOuterClass.Order parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static data.OrderOuterClass.Order parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static data.OrderOuterClass.Order parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static data.OrderOuterClass.Order parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static data.OrderOuterClass.Order parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static data.OrderOuterClass.Order parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static data.OrderOuterClass.Order parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static data.OrderOuterClass.Order parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static data.OrderOuterClass.Order parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static data.OrderOuterClass.Order parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static data.OrderOuterClass.Order parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(data.OrderOuterClass.Order prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
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
     * Protobuf type {@code order.Order}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:order.Order)
        data.OrderOuterClass.OrderOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return data.OrderOuterClass.internal_static_order_Order_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return data.OrderOuterClass.internal_static_order_Order_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                data.OrderOuterClass.Order.class, data.OrderOuterClass.Order.Builder.class);
      }

      // Construct using data.OrderOuterClass.Order.newBuilder()
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
      public Builder clear() {
        super.clear();
        confirmation_ = false;
        bitField0_ = (bitField0_ & ~0x00000001);
        type_ = false;
        bitField0_ = (bitField0_ & ~0x00000002);
        symbol_ = "";
        bitField0_ = (bitField0_ & ~0x00000004);
        quantity_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        price_ = 0D;
        bitField0_ = (bitField0_ & ~0x00000010);
        user_ = "";
        bitField0_ = (bitField0_ & ~0x00000020);
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return data.OrderOuterClass.internal_static_order_Order_descriptor;
      }

      public data.OrderOuterClass.Order getDefaultInstanceForType() {
        return data.OrderOuterClass.Order.getDefaultInstance();
      }

      public data.OrderOuterClass.Order build() {
        data.OrderOuterClass.Order result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public data.OrderOuterClass.Order buildPartial() {
        data.OrderOuterClass.Order result = new data.OrderOuterClass.Order(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.confirmation_ = confirmation_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.type_ = type_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.symbol_ = symbol_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.quantity_ = quantity_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.price_ = price_;
        if (((from_bitField0_ & 0x00000020) == 0x00000020)) {
          to_bitField0_ |= 0x00000020;
        }
        result.user_ = user_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof data.OrderOuterClass.Order) {
          return mergeFrom((data.OrderOuterClass.Order)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(data.OrderOuterClass.Order other) {
        if (other == data.OrderOuterClass.Order.getDefaultInstance()) return this;
        if (other.hasConfirmation()) {
          setConfirmation(other.getConfirmation());
        }
        if (other.hasType()) {
          setType(other.getType());
        }
        if (other.hasSymbol()) {
          bitField0_ |= 0x00000004;
          symbol_ = other.symbol_;
          onChanged();
        }
        if (other.hasQuantity()) {
          setQuantity(other.getQuantity());
        }
        if (other.hasPrice()) {
          setPrice(other.getPrice());
        }
        if (other.hasUser()) {
          bitField0_ |= 0x00000020;
          user_ = other.user_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        if (!hasConfirmation()) {
          return false;
        }
        if (!hasType()) {
          return false;
        }
        if (!hasSymbol()) {
          return false;
        }
        if (!hasQuantity()) {
          return false;
        }
        if (!hasPrice()) {
          return false;
        }
        if (!hasUser()) {
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        data.OrderOuterClass.Order parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (data.OrderOuterClass.Order) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private boolean confirmation_ ;
      /**
       * <code>required bool confirmation = 1;</code>
       */
      public boolean hasConfirmation() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required bool confirmation = 1;</code>
       */
      public boolean getConfirmation() {
        return confirmation_;
      }
      /**
       * <code>required bool confirmation = 1;</code>
       */
      public Builder setConfirmation(boolean value) {
        bitField0_ |= 0x00000001;
        confirmation_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required bool confirmation = 1;</code>
       */
      public Builder clearConfirmation() {
        bitField0_ = (bitField0_ & ~0x00000001);
        confirmation_ = false;
        onChanged();
        return this;
      }

      private boolean type_ ;
      /**
       * <pre>
       *true -&gt; BUY false -&gt; SELL
       * </pre>
       *
       * <code>required bool type = 2;</code>
       */
      public boolean hasType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <pre>
       *true -&gt; BUY false -&gt; SELL
       * </pre>
       *
       * <code>required bool type = 2;</code>
       */
      public boolean getType() {
        return type_;
      }
      /**
       * <pre>
       *true -&gt; BUY false -&gt; SELL
       * </pre>
       *
       * <code>required bool type = 2;</code>
       */
      public Builder setType(boolean value) {
        bitField0_ |= 0x00000002;
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *true -&gt; BUY false -&gt; SELL
       * </pre>
       *
       * <code>required bool type = 2;</code>
       */
      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        type_ = false;
        onChanged();
        return this;
      }

      private java.lang.Object symbol_ = "";
      /**
       * <code>required string symbol = 3;</code>
       */
      public boolean hasSymbol() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>required string symbol = 3;</code>
       */
      public java.lang.String getSymbol() {
        java.lang.Object ref = symbol_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            symbol_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string symbol = 3;</code>
       */
      public com.google.protobuf.ByteString
          getSymbolBytes() {
        java.lang.Object ref = symbol_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          symbol_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string symbol = 3;</code>
       */
      public Builder setSymbol(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        symbol_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string symbol = 3;</code>
       */
      public Builder clearSymbol() {
        bitField0_ = (bitField0_ & ~0x00000004);
        symbol_ = getDefaultInstance().getSymbol();
        onChanged();
        return this;
      }
      /**
       * <code>required string symbol = 3;</code>
       */
      public Builder setSymbolBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        symbol_ = value;
        onChanged();
        return this;
      }

      private int quantity_ ;
      /**
       * <code>required int32 quantity = 4;</code>
       */
      public boolean hasQuantity() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>required int32 quantity = 4;</code>
       */
      public int getQuantity() {
        return quantity_;
      }
      /**
       * <code>required int32 quantity = 4;</code>
       */
      public Builder setQuantity(int value) {
        bitField0_ |= 0x00000008;
        quantity_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 quantity = 4;</code>
       */
      public Builder clearQuantity() {
        bitField0_ = (bitField0_ & ~0x00000008);
        quantity_ = 0;
        onChanged();
        return this;
      }

      private double price_ ;
      /**
       * <code>required double price = 5;</code>
       */
      public boolean hasPrice() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>required double price = 5;</code>
       */
      public double getPrice() {
        return price_;
      }
      /**
       * <code>required double price = 5;</code>
       */
      public Builder setPrice(double value) {
        bitField0_ |= 0x00000010;
        price_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required double price = 5;</code>
       */
      public Builder clearPrice() {
        bitField0_ = (bitField0_ & ~0x00000010);
        price_ = 0D;
        onChanged();
        return this;
      }

      private java.lang.Object user_ = "";
      /**
       * <code>required string user = 6;</code>
       */
      public boolean hasUser() {
        return ((bitField0_ & 0x00000020) == 0x00000020);
      }
      /**
       * <code>required string user = 6;</code>
       */
      public java.lang.String getUser() {
        java.lang.Object ref = user_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            user_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string user = 6;</code>
       */
      public com.google.protobuf.ByteString
          getUserBytes() {
        java.lang.Object ref = user_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          user_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string user = 6;</code>
       */
      public Builder setUser(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
        user_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string user = 6;</code>
       */
      public Builder clearUser() {
        bitField0_ = (bitField0_ & ~0x00000020);
        user_ = getDefaultInstance().getUser();
        onChanged();
        return this;
      }
      /**
       * <code>required string user = 6;</code>
       */
      public Builder setUserBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000020;
        user_ = value;
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:order.Order)
    }

    // @@protoc_insertion_point(class_scope:order.Order)
    private static final data.OrderOuterClass.Order DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new data.OrderOuterClass.Order();
    }

    public static data.OrderOuterClass.Order getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<Order>
        PARSER = new com.google.protobuf.AbstractParser<Order>() {
      public Order parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new Order(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Order> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Order> getParserForType() {
      return PARSER;
    }

    public data.OrderOuterClass.Order getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_order_Order_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_order_Order_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\013order.proto\022\005order\"j\n\005Order\022\024\n\014confirm" +
      "ation\030\001 \002(\010\022\014\n\004type\030\002 \002(\010\022\016\n\006symbol\030\003 \002(" +
      "\t\022\020\n\010quantity\030\004 \002(\005\022\r\n\005price\030\005 \002(\001\022\014\n\004us" +
      "er\030\006 \002(\tB\006\n\004data"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_order_Order_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_order_Order_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_order_Order_descriptor,
        new java.lang.String[] { "Confirmation", "Type", "Symbol", "Quantity", "Price", "User", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
