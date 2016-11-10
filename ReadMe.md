#Software Risk Management

#Usage
1. Clone the project, waiting for maven dependencies downloaded.
2. Use the network in nju-software.
3. https://192.168.47.29:11111/SRM2/API

#SRM-api
Prefix: https://ip:port/SRM2/
##Project
    getProject: userName
    getDeveloper: projectId
    addDeveloper: projectId, developerName
    delDeveloper: userName
    addProject: Project(projectId, projectName, creatorName)
    delProject: projectId
    modifyProject: Project(projectId, projectName, creatorName)

##User
    addUser: User(userName, password, position, company)
    verifyUser: userName, password
    getColleague: company
    
##Risk
    getRisk: projectId
    addRisk: Risk(riskId, possibility, impact, threshold, 
    creatorName, followerName, description,riskType, projectId)
    delRisk: riskId
    modifyRisk: Risk(riskId, possibility, impact, threshold, 
    creatorName, followerName, description,riskType, projectId)

##Followup
    getFollowup: riskId
    addFollowup: Followup(followupId, status, followupDate, riskId)
    modifyFollowup: Followup(followupId, status, followupDate, riskId)
    delFollowup: followupId

#需求分析（第一阶段）

##商品功能：
	SF1.项目管理：项目的发布，修改，删除，参与，项目人员的分配
	SF2.风险管理：风险条目（包括风险内容、可能性（高中低）、影响程度（高中低）、触发器/阈值、提交者、跟踪者等信息）的输入，修改，删除
    SF3.用户管理：用户的注册，登录
    SF4.风险跟踪：跟踪记录的输入，修改，删除
##用户界面：
	UI1.用户登陆界面
		UI1.1注册界面
		UI1.2登陆界面
	UI2.系统主界面：显示项目列表，近期更新动态
        UI2.1.项目详情界面：显示项目对应的风险条目
			UI2.1.1.风险详情界面：列表显示风险条目；列表显示跟踪条目
			UI2.1.1.1跟踪详情界面：包括状态描述（风险？问题？）和相应的文本形式的描述信息；
		UI2.2动态展示界面：显示最新更新的条目，类别及对应项目
		
##Risk Type
    Management Risk, Legal Risk, Business Risk, Technical Risk, Quality Risk



##Service Interface:

###RiskService:

    addRisk(risk: Array[String])

    deleteRisk(riskId: String)

    modifyRisk(risk: Array[String])

    allRisk(projectId:String): Array[Risk]


###FollowupService:

    addFollowup(followup: Array[String])
   
    deleteFollowup(followupId: String)
    
    modifyFollowup(followup: Array[String])
    
    allFollowup(riskId:String): Array[Followup]

###UserService:

    addUser(user: Array[String])
    
    verifyUser(userId:String, password:String)

###ProjectService:
    addDeveloper(developerName:String, projectId:String)
    
    deleteDeveloper(developerName:String, projectId:String)
    
    projectDeveloper(projectId:String):Array[User]
    
    addProject(project: Array[String])
    
    deleteProject(projectId:String)
    
    modifyProject(project:Array[String])
    
    allProject(userName:String): Array[Project]
    

##Table Structure：

###Risk: 

    risk_id, possibility, impact(影响程度), trigger(触发器), creator_name, 
    follower_name, description, risk_type, project_id

###FollowUp(跟进): 

    followup_id, status(状态描述), follwup_date, risk_id

###User: 

    user_name, position, password, company

###project: 

    project_id, project_name

###project_developers: 

    project_id, developer_name
