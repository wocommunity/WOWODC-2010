package scala.womovies.model

import org.squeryl.annotations._
import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode._

class Talent(
		// attributes
		@Column("TALENT_ID") val id: Long,
		@Column("FIRST_NAME") val firstName: String,
		@Column("LAST_NAME") val lastName: String
	) extends KeyedEntity[Long] {

	// relationships
	lazy val moviesDirected = moviesToTalents.right(this)
}