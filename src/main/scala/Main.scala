import scala.reflect.ClassTag
import scala.util._

object Main {
  class A

  def constructAs[T <: A: ClassTag]: Try[T] = Try {
    new A()
  }.flatMap {
    case inst: T => Success(inst)
    case _ =>
      val tag = implicitly[ClassTag[T]]
      Failure(new ClassCastException(s"Failed to construct instance of class ${tag.runtimeClass.getName}"))
  }
}
