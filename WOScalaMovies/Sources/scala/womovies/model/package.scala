package scala.womovies.model

import org.squeryl.{KeyedEntity, Schema}
import org.squeryl.PrimitiveTypeMode._

/**
* DB schema
* @author ravim
*/
protected[model] object `package` extends Schema {
	val studios = table[Studio]("studio")
	val movies = table[Movie]("movie")
	val talent = table[Talent]("talent")
	val movieRoles = table[MovieRole]("movierole")

	// relations
	val moviesToStudio = oneToManyRelation(movies, studios).
	via((m, s) => m.studioId === s.id)

	val moviesToTalents = manyToManyRelation(movies, talent).
	via[Director]((m, t, d) => (d.movieID === m.id, d.talentID === t.id))

	val movieRolesToTalent = oneToManyRelation(movieRoles, talent).
	via((mr, t) => mr.talentID === t.id)

	val movieRolesToMovie = oneToManyRelation(movieRoles, movies).
	via((mr, m) => mr.movieID === m.id)
}