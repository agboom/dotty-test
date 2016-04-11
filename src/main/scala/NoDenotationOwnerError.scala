import play.api.libs.json._

object NoDenotationOwnerError {
  case class Foo()

  trait FooFormats {
    implicit val fooFormats: Format[Foo] = new Format[Foo] {
      override def reads(json: JsValue): JsResult[Foo] = JsSuccess(json.as[Foo])

      override def writes(o: Foo): JsValue = Json.toJson(o)
    }
  }
}
