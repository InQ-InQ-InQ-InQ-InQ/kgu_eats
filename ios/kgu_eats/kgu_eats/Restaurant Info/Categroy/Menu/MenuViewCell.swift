//
//  MenuViewCell.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/11.
//

import Foundation
import UIKit
class MenuViewCell: UICollectionViewCell{
    @IBOutlet weak var name: UILabel!
    @IBOutlet weak var price: UILabel!
    @IBOutlet weak var image: UIImageView!
    override func awakeFromNib() {
        super.awakeFromNib()
        self.image.layer.cornerRadius = 4
    }
    func updateUI(menu: Menu){
        self.name.text = menu.name
        self.price.text = "\(menu.price)"
        self.image.image = menu.image
    }
}
