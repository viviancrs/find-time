import SwiftUI

@main
struct TimezoneApp: App {

    init() {
        let tabBarItemAppearance = UITabBarItemAppearance()
        tabBarItemAppearance.configureWithDefault(for: .stacked)

        tabBarItemAppearance.normal.iconColor = .black
        tabBarItemAppearance.normal.titleTextAttributes = [.foregroundColor: UIColor.black]

        tabBarItemAppearance.selected.titleTextAttributes = [.foregroundColor: UIColor.white]
        tabBarItemAppearance.selected.iconColor = .white

        let appearance = UITabBarAppearance()
        appearance.configureWithOpaqueBackground()
        appearance.stackedLayoutAppearance = tabBarItemAppearance
        appearance.backgroundColor = .systemBlue

        UITabBar.appearance().standardAppearance = appearance
        UITabBar.appearance().scrollEdgeAppearance = appearance
    }

	var body: some Scene {
		WindowGroup {
			MainView()
		}
	}
}
