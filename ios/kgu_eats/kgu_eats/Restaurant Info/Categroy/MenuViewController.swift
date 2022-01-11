//
//  MenuViewController.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/01/10.
//

import UIKit

class MenuViewController: UIViewController {
    var cafeteria: Cafeteria?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    func delegateData(data: Int) {
        self.cafeteria = CafeteriaManager().getCafeteria(index: data)
    }
    
}
// TODO: datasource, delegate 구현

extension MenuViewController: UICollectionViewDelegate{
    
}
extension MenuViewController: UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return cafeteria?.menu.count ?? 0
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        return UICollectionViewCell()
    }
    
    
}
