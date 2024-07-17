//
//  View+ListModifier.swift
//  iosApp
//
//  Created by vivian.soares on 17/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ListModifier: ViewModifier {
    func body(content: Content) -> some View {
        if #available(iOS 15.0, *) {
            content
                .listRowInsets(.init())
                .listRowSeparator(.hidden)
        } else {
            content
        }
    }
}

extension View {
    func withListModifier() -> some View {
        modifier(ListModifier())
    }
}
