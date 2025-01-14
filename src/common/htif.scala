package Common

import Chisel._
import Node._
import Constants._
import Util._

class DebugIO extends Bundle
{
   val error_mode  = Bool(OUTPUT)
}


class PCRReq(addr_width: Int) extends Bundle
{
   val rw = Bool()
   val addr = Bits(width = addr_width)
   val data = Bits(width = 64)
   override def clone = { new PCRReq(addr_width).asInstanceOf[this.type] }
}


class HTIFIO() extends Bundle
{
   val reset = Bool(INPUT)
   val debug = new DebugIO
   val pcr_req = Decoupled(new PCRReq(addr_width = 6)).flip
   val pcr_rep = Decoupled(Bits(width = 64))

   val mem_req = Decoupled(new PCRReq(addr_width = 64)).flip
   val mem_rep = new Valid(Bits(width = 64))
}


class SCRIO extends Bundle
{
   val n = 64
   val rdata = Vec.fill(n) { Bits(INPUT, 64) }
   val wen = Bool(OUTPUT)
   val waddr = UInt(OUTPUT, log2Up(n))
   val wdata = Bits(OUTPUT, 64)
}
