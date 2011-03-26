package scala.womovies.model

import org.squeryl.annotations._
import org.squeryl.KeyedEntity
import org.squeryl.dsl.CompositeKey2
import org.squeryl.PrimitiveTypeMode._

class MovieRole(
		//attributes
		@Column("TALENT_ID") val talentID: Long,
		@Column("MOVIE_ID") val movieID: Long,
		@Column("ROLE_NAME") val roleName: Option[String]
	) extends KeyedEntity[CompositeKey2[Long,Long]] {
	
	// zero-arg constructor necessary when there are optional fields
	def this() = this(0, 0, Some(""))
	
	def id = compositeKey(talentID, movieID)
	
	// toOne relationships
	lazy val toMovie = movieRolesToMovie.left(this)
	def movie = toMovie.head
	
	lazy val toTalent = movieRolesToTalent.left(this)
	def talent = toTalent.head
}
