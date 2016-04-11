import scala.reflect.ClassTag
import scala.util._

object ContextBoundTypeParam {
  class A

  def constructAs[T <: A: ClassTag] = Try {
    new A()
  }.flatMap {
    case inst: T => Success(inst)
    case _ =>
      val tag = implicitly[ClassTag[T]]
      Failure(new ClassCastException(s"Failed to construct instance of class ${tag.runtimeClass.getName}"))
  }
}
