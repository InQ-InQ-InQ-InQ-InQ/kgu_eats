//
//  ReviewImageViewCell.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/18.
//

import UIKit

class ReviewImageViewCell: UICollectionViewCell {
    
    @IBOutlet weak var selectedImage: UIImageView!
    
    
    func updateUI(image: UIImage){
        self.selectedImage.image = image
    }
}
