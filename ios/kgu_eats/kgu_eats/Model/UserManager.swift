//
//  UserManager.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/07.
//

import Foundation
import Alamofire

class UserManager{
    static let shared = UserManager()
    
    let loginRequestURL = "\(NetWork.baseURL)/auth/login"
    let signURequestURL = "\(NetWork.baseURL)/auth/join"
    
    
    init(){
        
    }
    
    func login(model: LoginInfoModel){
       
        AF.request(loginRequestURL, method: .post, parameters: model.parameters, encoding: JSONEncoding.default, headers: model.headers).response { response in
            guard let statusCode = response.response?.statusCode else{
                return
            }
            
            switch statusCode{
            case 200..<300:
                guard let loginToken = response.response?.allHeaderFields["X-AUTH-TOKEN"]! as? String else{
                    print("Token Error")
                    return
                }
                self.getLoginToken(tokenModel: LoginResponseModel(accessToken: loginToken))
            default:
                // TODO: 오류처리
                print("default")
            }
        }
    }
    
    
    
}
extension UserManager{
    func getLoginToken(tokenModel: LoginResponseModel){
        UserDefaults.standard.setValue(tokenModel.accessToken, forKey: "loginToken")
    }
}
