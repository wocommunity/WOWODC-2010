package scala.womovies.model

import org.squeryl.annotations._
import org.squeryl.KeyedEntity
import org.squeryl.PrimitiveTypeMode._

import java.util.Date

class Movie(
		// attributes
		@Column("MOVIE_ID") val id: Long,
		val title: String, 
		val category: String,
		@Column("STUDIO_ID") val studioId: Option[Long],
		val rated: Option[String],
		@Column("POSTER_NAME") val posterName: Option[String],
		@Column("TRAILER_NAME") val trailerName: Option[String],
		val revenue: Option[BigDecimal],
		@Column("DATE_RELEASED") val dateReleased: Option[Date]
	) extends KeyedEntity[Long] {
	
	// zero-arg constructor necessary when there are optional fields
	def this() = this(0, null, null, Some(0L), Some(""), Some(""), Some(""), Some(0F), Some(new Date()))			
	
	// toOne relationships
	lazy val toStudio = moviesToStudio.left(this)
	def studio = toStudio.headOption

	// toMany relationships
	lazy val directors = moviesToTalents.left(this)
	
	lazy val roles = movieRolesToMovie.right(this)
}

object Movie {
	
    // queries
	def fetch(title: Option[String], category: Option[String]) = 
		from (movies) (m => 
			where (
					(m.title like title.?) and
					(m.category === category.?)
			) select(m))
			
	def fetchAll = from (movies) (select(_))
	
	// aggregates
	def tally: Int = {
		from (movies) (m => compute(count)).head.measures.toInt
	}
}
