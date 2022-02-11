//
//  MenuViewController.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/10.
//

import UIKit

class MenuViewController: UIViewController {
    
    var cafeteria: Cafeteria?
    
    @IBOutlet weak var collectionView: UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        collectionView.dataSource = self
        collectionView.delegate = self
        // Do any additional setup after loading the view.
    }
}
// TODO: datasource, delegate 구현

extension MenuViewController: UICollectionViewDelegate{
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        guard let vc = self.storyboard?.instantiateViewController(withIdentifier: "menuClicked") as? MenuClickedViewController else{
            return
        }
        vc.showMenu = cafeteria?.menu[indexPath.item]
    }
    
    
    
    
    
}
extension MenuViewController: UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        
        return cafeteria?.menu.count ?? 0
    }
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "menuList", for: indexPath) as? MenuViewCell else{
            return UICollectionViewCell()
        }
        guard let item = cafeteria?.menu[indexPath.row] else{
            return UICollectionViewCell()
        }
        cell.updateUI(menu: item)
        return cell
    }
}
extension MenuViewController: UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let height: CGFloat = 110
        let width = self.collectionView.bounds.width - 20

        return CGSize(width: width, height: height)
    }
}
