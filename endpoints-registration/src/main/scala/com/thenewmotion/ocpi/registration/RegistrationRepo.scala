package com.thenewmotion.ocpi
package registration

import msgs.{AuthToken, GlobalPartyId}
import msgs.Ownership.Theirs
import msgs.Versions.{Endpoint, VersionNumber}
import msgs.v2_1.Credentials.Creds
import scala.concurrent.{ExecutionContext, Future}

trait RegistrationRepo {

  def isPartyRegistered(globalPartyId: GlobalPartyId)(implicit ec: ExecutionContext): Future[Boolean]

  def findTheirAuthToken(globalPartyId: GlobalPartyId)(implicit ec: ExecutionContext): Future[Option[AuthToken[Theirs]]]

  // Called after a 3rd party has called our credentials endpoint with a POST
  def persistNewCredsResult(
    globalPartyId: GlobalPartyId,
    version: VersionNumber,
    token: AuthToken[Theirs],
    creds: Creds[Theirs],
    endpoints: Iterable[Endpoint]
  )(implicit ec: ExecutionContext): Future[Unit]

  // Called after a 3rd party has called our credentials endpoint with a PUT
  def persistUpdateCredsResult(
    globalPartyId: GlobalPartyId,
    version: VersionNumber,
    token: AuthToken[Theirs],
    creds: Creds[Theirs],
    endpoints: Iterable[Endpoint]
  )(implicit ec: ExecutionContext): Future[Unit]

  // Called after _we_ start the registration by calling _their_ credentials endpoint with a POST
  def persistRegistrationInitResult(
    version: VersionNumber,
    token: AuthToken[Theirs],
    creds: Creds[Theirs],
    endpoints: Iterable[Endpoint]
  )(implicit ec: ExecutionContext): Future[Unit]
}
