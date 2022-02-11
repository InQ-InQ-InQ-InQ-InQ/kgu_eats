//
//  Cafeteria.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/28.
//

import Foundation
import UIKit

class Cafeteria{
    let campusId: Int
    let id: Int
    let name: String // 가게명
    
    var info: Info?
    var menu: [Menu] = []
    var review: [Review] = []
    
    
    init(campusId: Int, id: Int, name: String){
        self.campusId = campusId
        self.id = id
        self.name = name
    }
    var thumnailImage: UIImage?{ // 가게 섬네일 사진
        return restaurantImage?.first
    }
    
    var restaurantImage: [UIImage]?{ // 가게 사진
        var temp: [UIImage] = []
        temp.append(UIImage(named: self.name))
        return temp
    }
}



