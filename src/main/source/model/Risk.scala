package model

/**
  * Created by ReggieYang on 2016/11/6.
  */
case class Risk(riskId: Int, possibility: String, impact: String, threshold: String,
                creatorName: String, followerName: String, description: String,
                riskType: String, projectId: Int)