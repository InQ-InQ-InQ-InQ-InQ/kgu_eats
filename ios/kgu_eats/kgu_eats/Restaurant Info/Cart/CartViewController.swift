//
//  CartViewController.swift
//  kgu_eats
//
//  Created by 유현진 on 2022/02/14.
//

import UIKit

class CartViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        self.collectionView.dataSource = self
        // Do any additional setup after loading the view.
        setUI()
    }
    
    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var restaurantName: UILabel!
    @IBOutlet weak var totalCost: UILabel!
    
    @IBAction func payTicket(_ sender: Any) {
        // TODO: ticket request
        let model = BuyTicketModel(token: UserDefaults.standard.string(forKey: "loginToken")!, cart: Cart.shared.self)
        Cart.shared.buyTicketRequest(model: model)
        self.dismiss(animated: true)
    }
    
    func updateUI(){
        self.totalCost.text = String(Cart.shared.getTotalCost())+"원"
    }
    func setUI(){
        self.restaurantName.text = CafeteriaManager.shared.getCafeteriaName(id: Cart.shared.currentRestaurantId)
        self.totalCost.text = String(Cart.shared.getTotalCost())+"원"
    }
}

extension CartViewController: UICollectionViewDataSource{
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return Cart.shared.cart.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "cartList", for: indexPath) as? CartCollectionViewCell else{
            return UICollectionViewCell()
        }
        let ticket = Cart.shared.cart[indexPath.item]
        cell.ticket = ticket
        cell.parentView = self
        cell.setUI(item: ticket)
        return cell
    }
}

extension CartViewController: UICollectionViewDelegateFlowLayout{
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = collectionView.bounds.width - 20 * 2
        let height: CGFloat = 244
        return CGSize(width: width, height: height)
    }
}
