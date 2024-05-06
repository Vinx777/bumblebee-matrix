package scalikejdbc

object SQLUtil {

  def sql[A](syntax: SQLSyntax): SQL[A, NoExtractor] =
    SQL[A](syntax.value).bind(syntax.rawParameters.toSeq: _*)
}
