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
    

    var cafeteriaId: Int?
    var pickerImageList: [UIImage] = []
    
    var finishedAddReview: ReviewViewController?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        reviewImageView.dataSource = self
        reviewImageView.delegate = self
        // Do any additional setup after loading the view.
        
//        self.name.text = CafeteriaManager.shared.getCafeteria(index: cafeteriaId!).name
    }
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.content.resignFirstResponder()
    }
    
    @IBAction func commitButton(_ sender: Any) {
        
        guard let newContent = self.content.text, newContent.count > 1 else{
            return
        }
        //let newReview = Review(name: "몰라", content: newContent, images: pickerImageList)
//        CafeteriaManager.shared.getCafeteria(index: cafeteriaId!).addReview(review: newReview)
        finishedAddReview?.reloadUI()
        self.dismiss(animated: true, completion: nil)
    }
    @IBAction func cancelButton(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
}

extension WriteReviewViewController: PHPickerViewControllerDelegate{
    
    func setConfig(){
        pickerImageList.removeAll()
        var configuration = PHPickerConfiguration()
        configuration.selectionLimit = 5
        configuration.filter = .any(of: [.images])
        let picker = PHPickerViewController(configuration: configuration)
        picker.delegate = self
        self.present(picker, animated: true, completion: nil)
    }
    
    func picker(_ picker: PHPickerViewController, didFinishPicking results: [PHPickerResult]) {
        let itemProvider = results.map(\.itemProvider)
        
        for image in itemProvider{
            if image.canLoadObject(ofClass: UIImage.self){
                image.loadObject(ofClass: UIImage.self) { image, error in
                    DispatchQueue.main.async {
                        guard let image = image as? UIImage else{ return }
                        self.pickerImageList.append(image)
                        self.reviewImageView.reloadData()
                    }
                }
            }
        }
        picker.dismiss(animated: true, completion: nil)
    }
}

extension WriteReviewViewController: UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return pickerImageList.count+1
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {

        if indexPath.item == 0 {
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "addImage", for: indexPath) as? AddImageButtonCollectionViewCell else{
                return UICollectionViewCell()
            }
            cell.pickerDelegate(delegate: self)
            return cell
        }else{
            guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "reviewImage", for: indexPath) as? ReviewImageViewCell else{
                return UICollectionViewCell()
            }
            cell.updateUI(image: self.pickerImageList[indexPath.item-1])
            
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
