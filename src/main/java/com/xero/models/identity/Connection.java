/*
 * Xero oAuth 2 identity service
 * This specifing endpoints related to managing authentication tokens and identity for Xero API
 *
 * The version of the OpenAPI document: 2.0.1
 * Contact: api@xero.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package com.xero.models.identity;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import org.threeten.bp.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Connection
 */

public class Connection {

  
  @JsonProperty("id")
  private UUID id;

  
  @JsonProperty("tenantId")
  private UUID tenantId;

  
  @JsonProperty("tenantType")
  private String tenantType;

  @JsonDeserialize(using = com.xero.api.CustomLocalDateTimeDeserializer.class)
  @JsonProperty("createdDateUTC")
  private LocalDateTime createdDateUTC;

  @JsonDeserialize(using = com.xero.api.CustomLocalDateTimeDeserializer.class)
  @JsonProperty("updatedDateUtc")
  private LocalDateTime updatedDateUtc;
  public Connection id(UUID id) {
    this.id = id;
    return this;
  }

   /**
   * Xero identifier
   * @return id
  **/
  @ApiModelProperty(value = "Xero identifier")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Connection tenantId(UUID tenantId) {
    this.tenantId = tenantId;
    return this;
  }

   /**
   * Xero identifier of organisation
   * @return tenantId
  **/
  @ApiModelProperty(value = "Xero identifier of organisation")
  public UUID getTenantId() {
    return tenantId;
  }

  public void setTenantId(UUID tenantId) {
    this.tenantId = tenantId;
  }

  public Connection tenantType(String tenantType) {
    this.tenantType = tenantType;
    return this;
  }

   /**
   * Xero tenant type (i.e. ORGANISATION, PRACTICE)
   * @return tenantType
  **/
  @ApiModelProperty(value = "Xero tenant type (i.e. ORGANISATION, PRACTICE)")
  public String getTenantType() {
    return tenantType;
  }

  public void setTenantType(String tenantType) {
    this.tenantType = tenantType;
  }

  public Connection createdDateUTC(LocalDateTime createdDateUTC) {
    this.createdDateUTC = createdDateUTC;
    return this;
  }

   /**
   * The date when the user connected this tenant to your app
   * @return createdDateUTC
  **/
  @ApiModelProperty(value = "The date when the user connected this tenant to your app")
  public LocalDateTime getCreatedDateUTC() {
    return createdDateUTC;
  }

  public void setCreatedDateUTC(LocalDateTime createdDateUTC) {
    this.createdDateUTC = createdDateUTC;
  }

  public Connection updatedDateUtc(LocalDateTime updatedDateUtc) {
    this.updatedDateUtc = updatedDateUtc;
    return this;
  }

   /**
   * The date when the user most recently connected this tenant to your app. May differ to the created date if the user has disconnected and subsequently reconnected this tenant to your app.
   * @return updatedDateUtc
  **/
  @ApiModelProperty(value = "The date when the user most recently connected this tenant to your app. May differ to the created date if the user has disconnected and subsequently reconnected this tenant to your app.")
  public LocalDateTime getUpdatedDateUtc() {
    return updatedDateUtc;
  }

  public void setUpdatedDateUtc(LocalDateTime updatedDateUtc) {
    this.updatedDateUtc = updatedDateUtc;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Connection connection = (Connection) o;
    return Objects.equals(this.id, connection.id) &&
        Objects.equals(this.tenantId, connection.tenantId) &&
        Objects.equals(this.tenantType, connection.tenantType) &&
        Objects.equals(this.createdDateUTC, connection.createdDateUTC) &&
        Objects.equals(this.updatedDateUtc, connection.updatedDateUtc);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tenantId, tenantType, createdDateUTC, updatedDateUtc);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Connection {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    tenantType: ").append(toIndentedString(tenantType)).append("\n");
    sb.append("    createdDateUTC: ").append(toIndentedString(createdDateUTC)).append("\n");
    sb.append("    updatedDateUtc: ").append(toIndentedString(updatedDateUtc)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

