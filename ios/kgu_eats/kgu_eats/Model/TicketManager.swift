//
//  TicketManager.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/15.
//

import Foundation
import Alamofire
class TicketManager{
    static let shared = TicketManager()
    var tickets = [Ticket]()
    let getTicketListByStore = "\(NetWork.baseURL)/tickets/store"
    let orderMenuRequestUrl = "\(NetWork.baseURL)/order/menu"
}


extension TicketManager{
    func getTicketListByStore(model: GetTicketListByStoreModel,  completion: @escaping([GetTicketListByStoreResponseModel]) -> Void){
        AF.request(getTicketListByStore+"/\(model.restuarantId)", method: .get, parameters: [:], encoding: URLEncoding.default, headers: model.headers).response { response in
            guard let statusCode = response.response?.statusCode else{
                print("get statusCode Error")
                return
            }
            
            switch statusCode{
            case 200..<300:
                do{
                    let decodeData = try JSONDecoder().decode([GetTicketListByStoreResponseModel].self, from: response.data!)
                    completion(decodeData)
                }catch{
                    print("getTicketListByStore() decode error: \(error)")
                }
            default:
                print("dafault")
            }
        }
    }
    
    
    
    func orderMenuRequest(model: OrderMenuModel){
        AF.request(orderMenuRequestUrl, method: .post, parameters: model.parameters, encoding: JSONEncoding.default, headers: model.headers).response { response in
            guard let statusCode = response.response?.statusCode else{
                print("get statusCode Error")
                return
            }
            switch statusCode{
            case 200..<300:
                do{
                    let decodeData = try JSONDecoder().decode(OrderMenuResponseModel.self, from: response.data!)
                    
                }catch{
                    print("OrderMenuRequest() decoder error")
                }
            default:
                print("default")
            }
        }
    }
    
}
//{
//    "id": 1,
//    "orderDate": "2022-02-15 08:37:02",
//    "orderTicketHistoryUnitDtos": [
//        {
//            "ticketId": 1,
//            "amount": 1
//        },
//        {
//            "ticketId": 2,
//            "amount": 2
//        }
//    ]
//}
