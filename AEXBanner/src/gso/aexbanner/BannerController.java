/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gso.aexbanner;

/**
 *
 * @author Julius op den Brouw
 */
public class BannerController {
    
    private AEXBanner banner;
    private IEffectenbeurs effectenbeurs;
    
    public BannerController(AEXBanner banner, IEffectenbeurs effectenbeurs)
    {
        this.banner = new AEXBanner();
        this.effectenbeurs = effectenbeurs;
    }
}
