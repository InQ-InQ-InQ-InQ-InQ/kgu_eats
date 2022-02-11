//
//  loginInfoModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/07.
//

import Foundation
import Alamofire

struct LoginInfoModel{
    var studentId: Int
    var password: String
    
    init(studentId: Int, password: String){
        self.studentId = studentId
        self.password = password
        
        self.parameters["studentId"] = studentId
        self.parameters["password"] = password
    }
    
    var parameters: Parameters = [:]
    
    var headers: HTTPHeaders = ["Content-Type":"application/json", "accept":"*/*"]
}
