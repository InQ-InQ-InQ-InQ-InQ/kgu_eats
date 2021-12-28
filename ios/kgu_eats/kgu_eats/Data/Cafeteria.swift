//
//  Cafeteria.swift
//  kgu_eats
//
//  Created by 유현진 on 2021/12/28.
//

import Foundation
import UIKit

struct Cafeteria{
    let name: String
    let menu: [Menu]
    var restaurantImage: [UIImage]?{
        var temp: [UIImage] = []
        temp.append(UIImage(named: "기숙사")!)
        temp.append(UIImage(named: "이스퀘어")!)
        return temp
    }
    var thumnailImage: UIImage?{
        return restaurantImage?.first
    }
    init(name: String, menu: [Menu]){
        self.name = name
        self.menu = menu
    }
    
}

struct Menu{
    let name: String
    let price: Int
    
    init(name: String, price: Int){
        self.name = name
        self.price = price
    }
}
