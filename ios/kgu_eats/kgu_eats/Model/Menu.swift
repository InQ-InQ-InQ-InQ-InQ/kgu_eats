//
//  Menu.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/12.
//

import Foundation
import UIKit
struct Menu{
    let menuId: Int
    let storeId: Int
    let name: String
    let price: String
    var image: UIImage?
    init(menuId: Int, storeId: Int, name: String, price: String){
        self.menuId = menuId
        self.storeId = storeId
        self.name = name
        self.price = price
        self.image = UIImage(named: "_"+name)
    }
}
