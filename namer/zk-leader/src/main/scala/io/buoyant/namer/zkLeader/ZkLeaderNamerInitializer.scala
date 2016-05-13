package io.buoyant.namer.zkLeader

import com.fasterxml.jackson.annotation.JsonIgnore
import com.twitter.finagle.Stack.Params
import com.twitter.finagle.{Namer, Path}
import io.buoyant.namer.{NamerConfig, NamerInitializer}

class ZkLeaderNamerInitializer extends NamerInitializer {
  override def configClass = classOf[ZkLeaderNamerConfig]
  override def configId: String = "io.l5d.zkLeader"
}

case class ZkLeaderNamerConfig(hosts: String) extends NamerConfig {
  @JsonIgnore
  override def defaultPrefix: Path = Path.Utf8("io.l5d.zkLeader")

  @JsonIgnore
  override def newNamer(params: Params): Namer = new ZkLeaderNamer(prefix, hosts)
}