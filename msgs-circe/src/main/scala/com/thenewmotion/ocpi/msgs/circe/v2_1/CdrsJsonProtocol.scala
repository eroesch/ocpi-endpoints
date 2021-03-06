package com.thenewmotion.ocpi.msgs
package circe.v2_1

import v2_1.Cdrs._
import io.circe.generic.extras.semiauto._
import io.circe.{Decoder, Encoder}
import TariffsJsonProtocol._
import CommonJsonProtocol._
import LocationsJsonProtocol._

trait CdrsJsonProtocol {
  implicit val cdrIdE: Encoder[CdrId] = stringEncoder(_.value)
  implicit val cdrIdD: Decoder[CdrId] = tryStringDecoder(CdrId.apply)

  implicit val cdrDimensionE: Encoder[CdrDimension] = deriveEncoder
  implicit val cdrDimensionD: Decoder[CdrDimension] = deriveDecoder

  implicit val chargingPeriodE: Encoder[ChargingPeriod] = deriveEncoder
  implicit val chargingPeriodD: Decoder[ChargingPeriod] = deriveDecoder

  implicit val cdrE: Encoder[Cdr] = deriveEncoder
  implicit val cdrD: Decoder[Cdr] = deriveDecoder
}

object CdrsJsonProtocol extends CdrsJsonProtocol
