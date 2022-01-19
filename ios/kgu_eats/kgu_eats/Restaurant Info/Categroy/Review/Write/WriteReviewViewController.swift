//
//  WriteReviewViewController.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/18.
//

import UIKit
import PhotosUI
class WriteReviewViewController: UIViewController {

    @IBOutlet weak var name: UILabel!
    @IBOutlet weak var content: UITextField!
    
    @IBOutlet weak var reviewImageView: UICollectionView!
    var cafeteria: Cafeteria?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        reviewImageView.dataSource = self
        reviewImageView.delegate = self
        // Do any additional setup after loading the view.
    }
    
    
}
extension WriteReviewViewController: PHPickerViewControllerDelegate{
    
    func setConfig(){
        var configuration = PHPickerConfiguration()
        configuration.selectionLimit = 5
        configuration.filter = .any(of: [.images])
        let picker = PHPickerViewController(configuration: configuration)
        picker.delegate = self
        self.present(picker, animated: true, completion: nil)
    }
    
    func picker(_ picker: PHPickerViewController, didFinishPicking results: [PHPickerResult]) {
        
    }
    
    
}

extension WriteReviewViewController: UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 1
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {

        if indexPath.item == 0 {
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "addImage", for: indexPath) as? AddImageButtonCollectionViewCell else{
                return UICollectionViewCell()
            }
            cell.pickerDelegate(delegate: self)
            return cell
        }else{
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "reviewImage", for: indexPath) as? ReviewViewCell else{
                return UICollectionViewCell()
            }
    //        cell.updateUI()
            return cell
        }
    }
}


extension WriteReviewViewController: UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width: CGFloat = 80
        let height: CGFloat = 80
        return CGSize(width: width, height: height)
    }
}
