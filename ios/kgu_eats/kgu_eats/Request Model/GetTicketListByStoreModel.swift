//
//  getTicketListModel.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/17.
//

import Foundation
import Alamofire
struct GetTicketListByStoreModel{
    var headers: HTTPHeaders = []
    let restuarantId: Int
    init(token: String, id: Int){
        self.headers["X-AUTH-TOKEN"] = token
        self.restuarantId = id
    }
}
