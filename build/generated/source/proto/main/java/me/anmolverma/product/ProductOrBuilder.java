// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ProductService.proto

package me.anmolverma.product;

public interface ProductOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Product)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>repeated string imageUrls = 3;</code>
   * @return A list containing the imageUrls.
   */
  java.util.List<java.lang.String>
      getImageUrlsList();
  /**
   * <code>repeated string imageUrls = 3;</code>
   * @return The count of imageUrls.
   */
  int getImageUrlsCount();
  /**
   * <code>repeated string imageUrls = 3;</code>
   * @param index The index of the element to return.
   * @return The imageUrls at the given index.
   */
  java.lang.String getImageUrls(int index);
  /**
   * <code>repeated string imageUrls = 3;</code>
   * @param index The index of the value to return.
   * @return The bytes of the imageUrls at the given index.
   */
  com.google.protobuf.ByteString
      getImageUrlsBytes(int index);

  /**
   * <code>.Category category = 4;</code>
   * @return The enum numeric value on the wire for category.
   */
  int getCategoryValue();
  /**
   * <code>.Category category = 4;</code>
   * @return The category.
   */
  me.anmolverma.product.Category getCategory();
}
