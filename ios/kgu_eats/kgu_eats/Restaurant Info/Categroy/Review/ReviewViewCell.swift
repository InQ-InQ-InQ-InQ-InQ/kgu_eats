//
//  ReviewViewCell.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/12.
//

import UIKit

class ReviewViewCell: UICollectionViewCell {
  
    @IBOutlet weak var menuName: UILabel!
    @IBOutlet weak var thumbnailImage: UIImageView!
    
    func updateUI(review: Review){
        self.menuName.text = review.name
        self.thumbnailImage.image = review.images.first
    }
    
}

