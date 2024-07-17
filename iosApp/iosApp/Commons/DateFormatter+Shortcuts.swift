//
//  DateFormatter+.swift
//  iosApp
//
//  Created by vivian.soares on 17/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

extension DateFormatter {
    static let shortTime: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateStyle = .none
        formatter.timeStyle = .short
        return formatter
    }()

    static let longDate: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateStyle = .long
        formatter.timeStyle = .none
        return formatter
    }()
}
