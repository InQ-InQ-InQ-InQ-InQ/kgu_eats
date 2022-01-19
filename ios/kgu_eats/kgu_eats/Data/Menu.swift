//
//  Menu.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/12.
//

import Foundation
import UIKit
struct Menu{
    let name: String
    let price: String
    var image: UIImage?
    init(name: String, price: String){
        self.name = name
        self.price = price
        self.image = UIImage(named: "_"+name)
    }
}
