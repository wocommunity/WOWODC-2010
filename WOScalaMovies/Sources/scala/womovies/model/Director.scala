package scala.womovies.model

import org.squeryl.annotations._
import org.squeryl.KeyedEntity
import org.squeryl.dsl.CompositeKey2
import org.squeryl.PrimitiveTypeMode._

class Director(
		//attributes
		@Column("TALENT_ID") val talentID: Long,
		@Column("MOVIE_ID") val movieID: Long
	) extends KeyedEntity[CompositeKey2[Long,Long]] {
	
	def id = compositeKey(talentID, movieID)
}
	