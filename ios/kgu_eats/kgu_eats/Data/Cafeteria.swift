//
//  Cafeteria.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/28.
//

import Foundation
import UIKit

class Cafeteria{
    let CafeteriaId: Int
    let name: String // 가게명
    let info: Info
    var restaurantImage: [UIImage]?{ // 가게 사진
        var temp: [UIImage] = []
        temp.append(UIImage(named: "기숙사식당")!)
        temp.append(UIImage(named: "이스퀘어")!)
        return temp
    }
    var thumnailImage: UIImage?{ // 가게 섬네일 사진
        return restaurantImage?.first
    }
    
    let menu: [Menu]
    var review: [Review] = []
    
    init(name: String, menu: [Menu], info: Info, id: Int){
        self.name = name
        self.menu = menu
        self.info = info
        self.CafeteriaId = id
    }
    func addReview(review: Review){
        self.review.append(review)
    }
    func getId() -> Int{
        return CafeteriaId
    }
}



