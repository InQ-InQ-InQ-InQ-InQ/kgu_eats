//
//  CategroyViewCell.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/03.
//

import Foundation
import UIKit
class CategoryViewCell: UICollectionViewCell{
    
    @IBOutlet weak var categoryTitle: UILabel!
    
    func setTitle(index: Int){

        if index == 0 {
            categoryTitle.text = "메뉴"
        }else if index == 1 {
            categoryTitle.text = "리뷰"
        }else{
            categoryTitle.text = "정보"
        }
    }
    
}
