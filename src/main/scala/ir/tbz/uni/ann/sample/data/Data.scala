package ir.tbz.uni.ann.sample.data

object Data {
	
	lazy val andSampleData: List[SampleData] = createAndSampleData
	lazy val orSampleData: List[SampleData] = createOrSampleData
	
	private def createAndSampleData: List[SampleData] =
		List(
			SampleData(1, 1, 1),
			SampleData(1, -1, -1),
			SampleData(-1, 1, -1),
			SampleData(-1, -1, -1)
		)

	private def createOrSampleData: List[SampleData] =
		List(
			SampleData(1, 1, 1),
			SampleData(1, -1, 1),
			SampleData(-1, 1, 1),
			SampleData(-1, -1, -1)
		)
}
