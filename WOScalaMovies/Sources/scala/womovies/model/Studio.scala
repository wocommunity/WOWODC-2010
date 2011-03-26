package scala.womovies.model

import org.squeryl.annotations._
import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode._

class Studio (
		// attributes
		@Column("STUDIO_ID") val id: Long,
		val name: String,
		val budget: BigDecimal
	) extends KeyedEntity[Long] {
	
	// relationships
	lazy val movies = moviesToStudio.right(this)
}