//
//  User.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/03.
//

import Foundation
import Alamofire

class User: Codable{
    static let shared = User()
    
    var userName: String
    var studentId: Int
    var password: String

    var histroy: [TempTicket] = []
    
    
    init(){
        self.userName = ""
        self.studentId = 0
        self.password = ""
    }
    
    enum CodingKeys: String, CodingKey{
        case userName = "username"
        case studentId
        case password
    }

}
// headers: ["Content-Type":"application/json", "Accept":"application/json"]


//join
//{
//"studentId":201610010,
//"username":"sinber",
//"password":"qwe123!@#"
//}


// login
//{
//"studentId":201610010,
//"password":"qwe123!@#"
//}
