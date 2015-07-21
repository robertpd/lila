package lila.coach

import play.api.libs.json._

private[coach] object JSONWriters {

  implicit val SectionWriter = OWrites[GameSections.Section] { s =>
    Json.obj(
      "nb" -> s.nb,
      "nbAnalysed" -> s.nbAnalysed,
      "moveAvg" -> s.moveAvg,
      "acplAvg" -> s.acplAvg)
  }
  implicit val GameSectionsWriter = Json.writes[GameSections]
  implicit val BestWinWriter = Json.writes[Results.BestWin]
  implicit val ResultsWriter = Json.writes[Results]
  implicit val ColorResultsWriter = Json.writes[ColorResults]
  implicit val OpeningsMapWriter = Writes[Openings.OpeningsMap] { o => Json.toJson(o.m) }
  implicit val OpeningsWriter = Json.writes[Openings]
  implicit val PerfResultsBestRatingWriter = Json.writes[PerfResults.BestRating]
  implicit val PerfResultsStatusMapWriter = OWrites[Map[chess.Status, Int]] { m =>
    JsObject(m.map { case (status, i) => status.name -> JsNumber(i) })
  }
  implicit val PerfResultsStreakWriter = Json.writes[PerfResults.Streak]
  implicit val PerfResultsStatusScoresWriter = Json.writes[PerfResults.StatusScores]
  implicit val PerfResultsOutcomeStatusesWriter = Json.writes[PerfResults.OutcomeStatuses]
  implicit val PerfResultsWriter = Json.writes[PerfResults]
  implicit val PerfResultsPerfMapWriter = OWrites[Map[lila.rating.PerfType, PerfResults]] { m =>
    JsObject(m.map { case (pt, res) => pt.key -> PerfResultsWriter.writes(res) })
  }
  implicit val PerfResultsMapWriter = Json.writes[PerfResults.PerfResultsMap]
  implicit val UserStatWriter = Json.writes[UserStat]

  implicit val OpeningWriter: OWrites[chess.Opening] = OWrites { o =>
    Json.obj(
      "code" -> o.code,
      "name" -> o.name,
      "moves" -> o.moves
    )
  }

  implicit val OpeningFamilyWriter = Json.writes[OpeningFamily]
}
