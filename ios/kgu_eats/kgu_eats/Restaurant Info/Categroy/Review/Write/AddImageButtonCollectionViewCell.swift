//
//  AddImageButtonCollectionViewCell.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/19.
//

import UIKit
import PhotosUI
class AddImageButtonCollectionViewCell: UICollectionViewCell{
    
    var writeReviewViewController: WriteReviewViewController?
    func pickerDelegate(delegate: WriteReviewViewController){
        writeReviewViewController = delegate
    }
    
    @IBAction func tapAddImage(_ sender: UIButton) {
        writeReviewViewController?.setConfig() //오류
    }
    
    
}
