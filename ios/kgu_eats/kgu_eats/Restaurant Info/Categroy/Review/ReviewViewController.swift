//
//  ReviewViewController.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/10.
//

import UIKit

class ReviewViewController: UIViewController {
    var cafeteriaId: Int?
    @IBOutlet weak var collectionView: UICollectionView!
    
    //    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
//        if segue.identifier == "writeReview"{
//            let view = segue.destination as? WriteReviewViewController
//            view?.cafeteria = self.cafeteria
//        }
//    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        self.collectionView.dataSource = self
        self.collectionView.delegate = self
    }
    
    @IBAction func writeReviewButton(_ sender: Any) {
        guard let reviewVC = self.storyboard?.instantiateViewController(withIdentifier: "writeReview") as? WriteReviewViewController else{
            return
        }
        reviewVC.cafeteriaId = self.cafeteriaId
        reviewVC.finishedAddReview = self
        self.present(reviewVC, animated: true, completion: nil)
    }
    func reloadUI(){
        self.collectionView.reloadData()
    }
}
extension ReviewViewController: UICollectionViewDelegate{
    
}
extension ReviewViewController: UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
//        return CafeteriaManager.shared.getCafeteria(index: cafeteriaId!).review.count
        return 0
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "ReviewList", for: indexPath) as? ReviewViewCell else {
            return UICollectionViewCell()
        }
//        cell.updateUI(review: CafeteriaManager.shared.getCafeteria(index: self.cafeteriaId!).review[indexPath.item])
        return cell
    }
}
extension ReviewViewController: UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = (collectionView.bounds.width - (20 * 3)) / 2
        let height = width + 40
        return CGSize(width: width, height: height)
    }
}
